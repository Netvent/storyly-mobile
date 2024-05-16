﻿using System;
using CoreGraphics;
using UIKit;

namespace Storyly.Xamarin.iOS.Demo
{
    static class Constants
    {
        public const string StorylyToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";
    }

    public partial class ViewController : UIViewController
    {
        public ViewController(IntPtr handle) : base(handle)
        {
        }

        public override void ViewDidLoad()
        {
            base.ViewDidLoad();
            // Perform any additional setup after loading the view, typically from a nib.

            var storylyView = new StorylyView(new CGRect(0, 50, 414, 90))
            {
                StorylyInit = new StorylyInit(
                    Constants.StorylyToken,
                    new StorylyConfigBuilder()
                    .Build()),
                RootViewController = this,
                Delegate = new StorylyDelegateImpl()
            };
            View.AddSubview(storylyView);

            var storylyViewCustomization = new StorylyView(new CGRect(0, 180, 414, 130))
            {
                StorylyInit = new StorylyInit(
                    Constants.StorylyToken,
                    new StorylyConfigBuilder()
                        .SetStoryGroupStyling(new StorylyStoryGroupStylingBuilder().SetSize(StoryGroupSize.Small)
                            .Build())
                    .Build()),
                RootViewController = this,
                Delegate = new StorylyDelegateImpl()
            };
            View.AddSubview(storylyViewCustomization);
        }

        public override void DidReceiveMemoryWarning()
        {
            base.DidReceiveMemoryWarning();
            // Release any cached data, images, etc that aren't in use.
        }
    }

    public partial class StorylyDelegateImpl : StorylyDelegate
    {
        public override void StorylyLoaded(StorylyView storylyView, StoryGroup[] storyGroupList, StorylyDataSource dataSource)
        {
            Console.WriteLine($"StorylyLoaded:SGSize:{storyGroupList.Length}");
        }

        public override void StorylyActionClicked(StorylyView storylyView, UIViewController rootViewController, Story story)
        {
            Console.WriteLine($"StorylyActionClicked:ActionUrl:{story.Media.ActionUrl}");
        }

        public override void StorylyEvent(StorylyView storylyView, StorylyEvent @event, StoryGroup storyGroup, Story story, StoryComponent storyComponent)
        {
            Console.WriteLine($"StorylyEvent:StorylyEvent:");
            if (storyComponent != null)
            {
                if (storyComponent.Type == StoryComponentType.Emoji)
                {
                    StoryEmojiComponent emojiComponent = (StoryEmojiComponent)storyComponent;
                    if (emojiComponent != null)
                    {
                        Console.WriteLine($"StorylyEvent:StoryEmojiComponent:{emojiComponent.CustomPayload}");
                    }
                }
            }
        }
    }
}
