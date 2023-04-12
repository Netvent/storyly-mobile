package com.appsamurai.storyly.storyly_flutter

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.appsamurai.storyly.*
import com.appsamurai.storyly.analytics.StorylyEvent
import com.appsamurai.storyly.data.managers.product.STRProductItem
import com.appsamurai.storyly.data.managers.product.STRProductVariant
import com.appsamurai.storyly.styling.*
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import java.util.*

class FlutterStorylyViewFactory(private val messenger: BinaryMessenger) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    internal lateinit var context: Context

    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView = FlutterStorylyView(this.context, messenger, viewId, args as HashMap<String, Any>)
}

class FlutterStorylyView(
    private val context: Context,
    messenger: BinaryMessenger,
    viewId: Int,
    private val args: HashMap<String, Any>
) : PlatformView, StorylyListener {

    private val methodChannel: MethodChannel = MethodChannel(messenger, "com.appsamurai.storyly/flutter_storyly_view_$viewId").apply {
        setMethodCallHandler { call, _ ->
            val callArguments = call.arguments as? Map<String, *>
            when (call.method) {
                "refresh" -> storylyView.refresh()
                "show" -> storylyView.show()
                "dismiss" -> storylyView.dismiss()
                "openStory" -> storylyView.openStory(
                    callArguments?.get("storyGroupId") as? String ?: "",
                    callArguments?.getOrElse("storyId", { null }) as? String
                )
                "openStoryUri" -> storylyView.openStory(Uri.parse(callArguments?.get("uri") as? String))
                "setExternalData" -> (callArguments?.get("externalData") as List<Map<String, Any?>>)?.let { storylyView.setExternalData(it) }
                "hydrateProducts" -> (callArguments?.get("products") as List<Map<String, Any?>>)?.let {
                    val products = it.map { product -> createSTRProductItem(product) }
                    storylyView.hydrateProducts(products)
                }
            }
        }
    }

    companion object {
        private const val ARGS_STORYLY_ID = "storylyId"
        private const val ARGS_STORYLY_SEGMENTS = "storylySegments"
        private const val ARGS_STORYLY_USER_PROPERTY = "storylyUserProperty"
        private const val ARGS_STORYLY_PAYLOAD = "storylyPayload"
        private const val ARGS_STORYLY_CUSTOM_PARAMETERS = "storylyCustomParameters"
        private const val ARGS_STORYLY_SHARE_URL = "storylyShareUrl"
        private const val ARGS_STORYLY_IS_TEST_MODE = "storylyIsTestMode"

        private const val ARGS_STORYLY_BACKGROUND_COLOR = "storylyBackgroundColor"

        private const val ARGS_STORYLY_LAYOUT_DIRECTION = "storylyLayoutDirection"

        private const val ARGS_STORY_GROUP_ANIMATION = "storyGroupAnimation"
        private const val ARGS_STORY_GROUP_SIZE = "storyGroupSize"
        private const val ARGS_STORY_GROUP_ICON_STYLING = "storyGroupIconStyling"
        private const val ARGS_STORY_GROUP_LIST_STYLING = "storyGroupListStyling"
        private const val ARGS_STORY_GROUP_ICON_IMAGE_THEMATIC_LABEL = "storyGroupIconImageThematicLabel"
        private const val ARGS_STORY_GROUP_TEXT_STYLING = "storyGroupTextStyling"
        private const val ARGS_STORY_HEADER_STYLING = "storyHeaderStyling"

        private const val ARGS_STORY_GROUP_ICON_BORDER_COLOR_SEEN = "storyGroupIconBorderColorSeen"
        private const val ARGS_STORY_GROUP_ICON_BORDER_COLOR_NOT_SEEN = "storyGroupIconBorderColorNotSeen"
        private const val ARGS_STORY_GROUP_ICON_BACKGROUND_COLOR = "storyGroupIconBackgroundColor"
        private const val ARGS_STORY_GROUP_PIN_ICON_COLOR = "storyGroupPinIconColor"
        private const val ARGS_STORY_ITEM_ICON_BORDER_COLOR = "storyItemIconBorderColor"
        private const val ARGS_STORY_ITEM_TEXT_COLOR = "storyItemTextColor"
        private const val ARGS_STORY_ITEM_PROGRESS_BAR_COLOR = "storyItemProgressBarColor"
        private const val ARGS_STORY_ITEM_TEXT_TYPEFACE = "storyItemTextTypeface"
        private const val ARGS_STORY_INTERACTIVE_TEXT_TYPEFACE = "storyInteractiveTextTypeface"
    }

    private val storylyView: StorylyView by lazy {
        StorylyView(context).apply {
            val storylyId = args[ARGS_STORYLY_ID] as? String
                ?: throw Exception("StorylyId must be set.")
            val segments = args[ARGS_STORYLY_SEGMENTS] as? List<String>
            val isTestMode = args[ARGS_STORYLY_IS_TEST_MODE] as? Boolean ?: false
            val storylyPayload = args[ARGS_STORYLY_PAYLOAD] as? String
            val customParameters = args[ARGS_STORYLY_CUSTOM_PARAMETERS] as? String
            val userProperty = args[ARGS_STORYLY_USER_PROPERTY] as? Map<String, String> ?: null
            storylyInit = StorylyInit(
                storylyId,
                StorylySegmentation(segments = segments?.toSet()),
                isTestMode = isTestMode,
                storylyPayload = storylyPayload,
                customParameter = customParameters,
            ).apply {
                userProperty?.let { userData = it }
            }
            (args[ARGS_STORYLY_SHARE_URL] as? String)?.let { storylyShareUrl = it }

            (args[ARGS_STORY_GROUP_SIZE] as? String)?.let {
                setStoryGroupSize(
                    when (it) {
                        "small" -> StoryGroupSize.Small
                        "custom" -> StoryGroupSize.Custom
                        else -> StoryGroupSize.Large
                    }
                )
            }
            (args[ARGS_STORY_GROUP_ANIMATION] as? String)?.let {
                setStoryGroupAnimation(
                    when (it) {
                        "border-rotation" -> StoryGroupAnimation.BorderRotation
                        "disabled" -> StoryGroupAnimation.Disabled
                        else -> StoryGroupAnimation.BorderRotation
                    }
                )
            }
            (args[ARGS_STORYLY_BACKGROUND_COLOR] as? String)?.let { setBackgroundColor(Color.parseColor(it)) }
            (args[ARGS_STORY_GROUP_ICON_BORDER_COLOR_SEEN] as? List<String>)?.let { colors -> setStoryGroupIconBorderColorSeen(colors.map { color -> Color.parseColor(color) }.toTypedArray()) }
            (args[ARGS_STORY_GROUP_ICON_BORDER_COLOR_NOT_SEEN] as? List<String>)?.let { colors -> setStoryGroupIconBorderColorNotSeen(colors.map { color -> Color.parseColor(color) }.toTypedArray()) }
            (args[ARGS_STORY_GROUP_ICON_BACKGROUND_COLOR] as? String)?.let { setStoryGroupIconBackgroundColor(Color.parseColor(it)) }
            (args[ARGS_STORY_GROUP_PIN_ICON_COLOR] as? String)?.let { setStoryGroupPinIconColor(Color.parseColor(it)) }
            (args[ARGS_STORY_ITEM_ICON_BORDER_COLOR] as? List<String>)?.let { colors -> setStoryItemIconBorderColor(colors.map { color -> Color.parseColor(color) }.toTypedArray()) }
            (args[ARGS_STORY_ITEM_TEXT_COLOR] as? String)?.let { setStoryItemTextColor(Color.parseColor(it)) }
            (args[ARGS_STORY_ITEM_PROGRESS_BAR_COLOR] as? List<String>)?.let { colors -> setStoryItemProgressBarColor(colors.map { color -> Color.parseColor(color) }.toTypedArray()) }
            (args[ARGS_STORY_ITEM_TEXT_TYPEFACE] as? String)?.let { typeface ->
                val customTypeface = try {
                    Typeface.createFromAsset(context.applicationContext.assets, typeface)
                } catch (_: Exception) {
                    Typeface.DEFAULT
                }
                setStoryItemTextTypeface(customTypeface)
            }
            (args[ARGS_STORY_INTERACTIVE_TEXT_TYPEFACE] as? String)?.let { typeface ->
                val customTypeface = try {
                    Typeface.createFromAsset(context.applicationContext.assets, typeface)
                } catch (_: Exception) {
                    Typeface.DEFAULT
                }
                setStoryInteractiveTextTypeface(customTypeface)
            }

            (args[ARGS_STORY_GROUP_ICON_STYLING] as? Map<String, *>)?.let {
                val width = it["width"] as? Int ?: dpToPixel(80)
                val height = it["height"] as? Int ?: dpToPixel(80)
                val cornerRadius = it["cornerRadius"] as? Int ?: dpToPixel(40)
                setStoryGroupIconStyling(StoryGroupIconStyling(height.toFloat(), width.toFloat(), cornerRadius.toFloat()))
            }

            (args[ARGS_STORY_GROUP_LIST_STYLING] as? Map<String, *>)?.let {
                val orientation = when (it["orientation"] as? String) {
                    "horizontal" -> StoryGroupListOrientation.Horizontal
                    "vertical" -> StoryGroupListOrientation.Vertical
                    else -> StoryGroupListOrientation.Horizontal
                }
                val sections = it["sections"] as? Int ?: 1
                val horizontalEdgePadding = (it["horizontalEdgePadding"] as? Int) ?: dpToPixel(4)
                val verticalEdgePadding = (it["verticalEdgePadding"] as? Int) ?: dpToPixel(4)
                val horizontalPaddingBetweenItems = (it["horizontalPaddingBetweenItems"] as? Int) ?: dpToPixel(8)
                val verticalPaddingBetweenItems = (it["verticalPaddingBetweenItems"] as? Int) ?: dpToPixel(8)

                setStoryGroupListStyling(
                    StoryGroupListStyling(
                        orientation,
                        sections,
                        horizontalEdgePadding.toFloat(),
                        verticalEdgePadding.toFloat(),
                        horizontalPaddingBetweenItems.toFloat(),
                        verticalPaddingBetweenItems.toFloat()
                    )
                )
            }

            (args[ARGS_STORY_GROUP_ICON_IMAGE_THEMATIC_LABEL] as? String)?.let { setStoryGroupIconImageThematicLabel(it) }

            (args[ARGS_STORY_GROUP_TEXT_STYLING] as? Map<String, *>)?.let { it ->
                val isVisible = it["isVisible"] as? Boolean ?: true
                val textSize = it["textSize"] as? Int
                val lines = it["lines"] as? Int
                val typeface = it["typeface"] as? String
                val colorSeen = Color.parseColor(it["colorSeen"] as? String ?: "#FF000000")
                val colorNotSeen = Color.parseColor(it["colorNotSeen"] as? String ?: "#FF000000")

                val customTypeface = typeface?.let {
                    try {
                        Typeface.createFromAsset(context.applicationContext.assets, it)
                    } catch (_: Exception) {
                        null
                    }
                } ?: Typeface.DEFAULT

                setStoryGroupTextStyling(
                    StoryGroupTextStyling(
                        isVisible = isVisible,
                        typeface = customTypeface,
                        textSize = Pair(TypedValue.COMPLEX_UNIT_PX, textSize),
                        minLines = null,
                        maxLines = null,
                        lines = lines,
                        colorSeen = colorSeen,
                        colorNotSeen = colorNotSeen,
                    )
                )
            }

            (args[ARGS_STORY_HEADER_STYLING] as? Map<String, *>)?.let {
                val isTextVisible = it["isTextVisible"] as? Boolean ?: true
                val isIconVisible = it["isIconVisible"] as? Boolean ?: true
                val isCloseButtonVisible = it["isCloseButtonVisible"] as? Boolean ?: true
                val shareIcon = it["shareIcon"] as? String
                val closeIcon = it["closeIcon"] as? String

                val shareIconDrawable = shareIcon?.let { icon -> getDrawable(context.applicationContext, icon) }
                val closeIconDrawable = closeIcon?.let { icon -> getDrawable(context.applicationContext, icon) }

                setStoryHeaderStyling(StoryHeaderStyling(isTextVisible, isIconVisible, isCloseButtonVisible, closeIconDrawable, shareIconDrawable))
            }

            (args[ARGS_STORYLY_LAYOUT_DIRECTION] as? String)?.let {
                setStorylyLayoutDirection(
                    when (it) {
                        "ltr" -> StorylyLayoutDirection.LTR
                        "rtl" -> StorylyLayoutDirection.RTL
                        else -> StorylyLayoutDirection.LTR
                    }
                )
            }

            storylyProductListener = object : StorylyProductListener {
                override fun storylyEvent(storylyView: StorylyView, event: StorylyEvent, product: STRProductItem?, extras: Map<String, String>) {
                    methodChannel.invokeMethod(
                        "storylyProductEvent",
                        mapOf(
                            "event" to event.name,
                            "product" to product?.let { createSTRProductItemMap(it) },
                            "extras" to extras
                        )
                    )
                }

                override fun storylyHydration(storylyView: StorylyView, productIds: List<String>) {
                    methodChannel.invokeMethod(
                        "storylyOnHydration",
                        mapOf(
                            "productIds" to productIds
                        )
                    )
                }

            }

            storylyListener = object : StorylyListener {
                override fun storylyActionClicked(storylyView: StorylyView, story: Story) {
                    methodChannel.invokeMethod(
                        "storylyActionClicked",
                        createStoryMap(story)
                    )
                }

                override fun storylyLoaded(
                    storylyView: StorylyView,
                    storyGroupList: List<StoryGroup>,
                    dataSource: StorylyDataSource
                ) {
                    methodChannel.invokeMethod(
                        "storylyLoaded",
                        mapOf(
                            "storyGroups" to storyGroupList.map { storyGroup -> createStoryGroupMap(storyGroup) },
                            "dataSource" to dataSource.value
                        )
                    )
                }

                override fun storylyLoadFailed(
                    storylyView: StorylyView,
                    errorMessage: String
                ) {
                    methodChannel.invokeMethod("storylyLoadFailed", errorMessage)
                }

                override fun storylyEvent(
                    storylyView: StorylyView,
                    event: StorylyEvent,
                    storyGroup: StoryGroup?,
                    story: Story?,
                    storyComponent: StoryComponent?
                ) {
                    methodChannel.invokeMethod(
                        "storylyEvent",
                        mapOf("event" to event.name,
                            "storyGroup" to storyGroup?.let { createStoryGroupMap(storyGroup) },
                            "story" to story?.let { createStoryMap(story) },
                            "storyComponent" to storyComponent?.let { createStoryComponentMap(storyComponent) })
                    )
                }

                override fun storylyStoryShown(storylyView: StorylyView) {
                    methodChannel.invokeMethod("storylyStoryShown", null)
                }

                override fun storylyStoryDismissed(storylyView: StorylyView) {
                    methodChannel.invokeMethod("storylyStoryDismissed", null)
                }

                override fun storylyUserInteracted(
                    storylyView: StorylyView,
                    storyGroup: StoryGroup,
                    story: Story,
                    storyComponent: StoryComponent
                ) {
                    methodChannel.invokeMethod(
                        "storylyUserInteracted",
                        mapOf(
                            "storyGroup" to createStoryGroupMap(storyGroup),
                            "story" to createStoryMap(story),
                            "storyComponent" to createStoryComponentMap(storyComponent)
                        )
                    )
                }
            }
        }
    }

    override fun getView(): View {
        return storylyView
    }

    override fun dispose() {}

    private fun createStoryGroupMap(storyGroup: StoryGroup): Map<String, *> {
        return mapOf(
            "id" to storyGroup.uniqueId,
            "title" to storyGroup.title,
            "index" to storyGroup.index,
            "seen" to storyGroup.seen,
            "iconUrl" to storyGroup.iconUrl,
            "stories" to storyGroup.stories.map { story -> createStoryMap(story) },
            "thematicIconUrls" to storyGroup.thematicIconUrls,
            "coverUrl" to storyGroup.coverUrl,
            "pinned" to storyGroup.pinned,
            "type" to storyGroup.type.ordinal,
        )
    }

    private fun createStoryMap(story: Story): Map<String, *> {
        return mapOf("id" to story.uniqueId,
            "title" to story.title,
            "name" to story.name,
            "index" to story.index,
            "seen" to story.seen,
            "currentTime" to story.currentTime,
            "media" to story.media.let {
                mapOf(
                    "type" to it.type.ordinal,
                    "actionUrlList" to it.actionUrlList,
                    "actionUrl" to it.actionUrl,
                    "previewUrl" to it.previewUrl,
                    "storyComponentList" to it.storyComponentList?.map { component -> createStoryComponentMap(component) }
                )
            }
        )
    }

    private fun createStoryComponentMap(storyComponent: StoryComponent): Map<String, *> {
        when (storyComponent) {
            is StoryQuizComponent -> {
                return mapOf(
                    "type" to storyComponent.type.name.toLowerCase(Locale.ENGLISH),
                    "id" to storyComponent.id,
                    "title" to storyComponent.title,
                    "options" to storyComponent.options,
                    "rightAnswerIndex" to storyComponent.rightAnswerIndex,
                    "selectedOptionIndex" to storyComponent.selectedOptionIndex,
                    "customPayload" to storyComponent.customPayload
                )
            }
            is StoryPollComponent -> {
                return mapOf(
                    "type" to storyComponent.type.name.toLowerCase(Locale.ENGLISH),
                    "id" to storyComponent.id,
                    "title" to storyComponent.title,
                    "options" to storyComponent.options,
                    "selectedOptionIndex" to storyComponent.selectedOptionIndex,
                    "customPayload" to storyComponent.customPayload
                )
            }
            is StoryEmojiComponent -> {
                return mapOf(
                    "type" to storyComponent.type.name.toLowerCase(Locale.ENGLISH),
                    "id" to storyComponent.id,
                    "emojiCodes" to storyComponent.emojiCodes,
                    "selectedEmojiIndex" to storyComponent.selectedEmojiIndex,
                    "customPayload" to storyComponent.customPayload
                )
            }
            is StoryRatingComponent -> {
                return mapOf(
                    "type" to storyComponent.type.name.toLowerCase(Locale.ENGLISH),
                    "id" to storyComponent.id,
                    "emojiCode" to storyComponent.emojiCode,
                    "rating" to storyComponent.rating,
                    "customPayload" to storyComponent.customPayload
                )
            }
            is StoryPromoCodeComponent -> {
                return mapOf(
                    "type" to storyComponent.type.name.toLowerCase(Locale.ENGLISH),
                    "id" to storyComponent.id,
                    "text" to storyComponent.text
                )
            }
            is StoryCommentComponent -> {
                return mapOf(
                    "type" to storyComponent.type.name.toLowerCase(Locale.ENGLISH),
                    "id" to storyComponent.id,
                    "text" to storyComponent.text
                )
            }
            else -> {
                return mapOf(
                    "type" to storyComponent.type.name.toLowerCase(Locale.ENGLISH),
                    "id" to storyComponent.id,
                )
            }
        }
    }

    internal fun createSTRProductItemMap(product: STRProductItem): Map<String, *> {
        return mapOf(
            "productId" to product.productId,
            "productGroupId" to product.productGroupId,
            "title" to product.title,
            "desc" to product.desc,
            "price" to product.price.toDouble(),
            "salesPrice" to product.salesPrice?.toDouble(),
            "currency" to product.currency,
            "imageUrls" to product.imageUrls,
            "variants" to product.variants.map {
                createSTRProductVariantMap(it)
            }
        )
    }

    internal fun createSTRProductVariantMap(variant: STRProductVariant): Map<String, *> {
        return mapOf(
            "name" to variant.name,
            "value" to variant.value
        )
    }

    internal fun createSTRProductItem(product: Map<String, Any?>): STRProductItem {
        return STRProductItem(
            productId = product["productId"] as? String ?: "",
            productGroupId = product["productGroupId"] as? String ?: "",
            title = product["title"] as? String ?: "",
            desc = product["desc"] as? String ?: "",
            price = (product["price"] as Double).toFloat(),
            salesPrice = (product["salesPrice"] as? Double)?.toFloat(),
            currency = product["currency"] as? String ?: "",
            imageUrls = product["imageUrls"] as? List<String>,
            url = product["url"] as? String ?: "",
            variants = createSTRProductVariant(product["variants"] as? List<Map<String, Any?>>)
        )
    }

    internal fun createSTRProductVariant(variants: List<Map<String, Any?>>?): List<STRProductVariant> {
        return variants?.map { variant ->
            STRProductVariant(
                name = variant["name"] as? String ?: "",
                value = variant["value"] as? String ?: ""
            )
        } ?: listOf()
    }


    private fun dpToPixel(dpValue: Int): Float {
        return dpValue * (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }


    private fun getDrawable(context: Context, name: String): Drawable? {
        val id = context.resources.getIdentifier(name, "drawable", context.packageName)
        return ContextCompat.getDrawable(context, id)
    }
}