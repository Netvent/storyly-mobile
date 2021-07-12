import 'package:flutter/material.dart';
import 'package:storyly_flutter/storyly_flutter.dart';

class ShowStorylyPage extends StatefulWidget {
  _ShowStorylyPageState createState() => _ShowStorylyPageState();
}

class _ShowStorylyPageState extends State<ShowStorylyPage> {
  static const STORYLY_INSTANCE_TOKEN =
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

  bool storylyVisible = false;

  void onStorylyLoaded(List<dynamic> storyGroupList) {
    print("storylyLoaded");
    if (!storylyVisible) {
      setState(() {
        storylyVisible = true;
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
