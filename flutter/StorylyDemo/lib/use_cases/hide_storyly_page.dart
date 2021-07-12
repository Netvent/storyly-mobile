import 'package:flutter/material.dart';
import 'package:storyly_flutter/storyly_flutter.dart';

class HideStorylyPage extends StatefulWidget {
  _HideStorylyPageState createState() => _HideStorylyPageState();
}

class _HideStorylyPageState extends State<HideStorylyPage> {
  static const STORYLY_INSTANCE_TOKEN =
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

  bool storylyVisible = true;
  bool storylyLoaded = false;

  void onStorylyLoaded(List<dynamic> storyGroupList) {
    if (!storylyVisible && storyGroupList.length > 0) {
      storylyLoaded = true;
    }
  }

  void onStorylyLoadFailed(String err) {
    if (!storylyLoaded && storylyVisible) {
      setState(() {
        storylyVisible = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: Center(
        child: Column(
          children: <Widget>[
            Container(height: 120, color: const Color(0xFF00E0E4)),
            Container(height: 120, color: const Color(0xFF242450)),
            Visibility(
              visible: storylyVisible,
              maintainState: true,
              child: Container(
                height: 120,
                child: StorylyView(
                  androidParam: StorylyParam()
                    ..storylyId = STORYLY_INSTANCE_TOKEN,
                  iosParam: StorylyParam()..storylyId = STORYLY_INSTANCE_TOKEN,
                  storylyLoaded: onStorylyLoaded,
                  storylyLoadFailed: onStorylyLoadFailed,
                ),
              ),
            ),
            Container(height: 120, color: const Color(0xFFBDBDCB)),
            Container(height: 120, color: const Color(0xFFFFCB00)),
          ],
        ),
      ),
    );
  }
}
