import 'package:flutter/material.dart';
import 'package:storyly_flutter/storyly_flutter.dart';

class BasePage extends StatefulWidget {
  BasePage({Key key, this.title}) : super(key: key);

  final String title;

  _BasePageState createState() => _BasePageState();
}

class _BasePageState extends State<BasePage> {
  static const STORYLY_INSTANCE_TOKEN =
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

  StorylyViewController storylyViewController;

  void onStorylyViewCreated(StorylyViewController storylyViewController) {
    this.storylyViewController = storylyViewController;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Container(
              margin: const EdgeInsets.only(top: 10),
              height: 120,
              child: StorylyView(
                onStorylyViewCreated: onStorylyViewCreated,
                androidParam: StorylyParam()
                  ..storylyId = STORYLY_INSTANCE_TOKEN
                  ..storyGroupListEdgePadding = 20
                  ..storyGroupListPaddingBetweenItems = 20,
                iosParam: StorylyParam()
                  ..storylyId = STORYLY_INSTANCE_TOKEN
                  ..storyGroupListEdgePadding = 20
                  ..storyGroupListPaddingBetweenItems = 20,
                storylyLoaded: (storyGroupList) => print("storylyLoaded"),
                storylyLoadFailed: (errorMessage) => print("storylyLoadFailed"),
                storylyActionClicked: (story) => print("storylyActionClicked"),
                storylyEvent: (event) => print("storylyEvent"),
                storylyStoryShown: () => print("storylyStoryShown"),
                storylyStoryDismissed: () => print("storylyStoryDismissed"),
                storylyUserInteracted: (eventPayload) =>
                    print("storylyUserInteracted"),
              ),
            )
          ],
        ),
      ),
    );
  }
}
