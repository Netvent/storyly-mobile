import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:storyly_flutter/storyly_flutter.dart';

class UpdateStateExample extends StatefulWidget {
  @override
  _UpdateStateExampleState createState() => _UpdateStateExampleState();
}

class _UpdateStateExampleState extends State<UpdateStateExample> {
  static const STORYLY_INSTANCE_TOKEN =
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

  late StorylyViewController storylyViewController;

  bool isActive = true;
  List<Color> colors = [Colors.blue, Colors.green];

  void onStorylyViewCreated(StorylyViewController storylyViewController) {
    this.storylyViewController = storylyViewController;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Update State Example"),
      ),
      body: Column(
        children: [
          Container(
            margin: EdgeInsets.only(top: 5.0),
            height: 120,
            child: isActive
                ? StorylyView(
                    onStorylyViewCreated: onStorylyViewCreated,
                    androidParam: StorylyParam()
                      ..storyGroupPinIconColor = Colors.green
                      ..storyGroupIconBorderColorNotSeen = colors
                      ..storyGroupIconBorderColorSeen = colors
                      ..storylyId = STORYLY_INSTANCE_TOKEN
                      ..storylyTestMode = true,
                    iosParam: StorylyParam()
                      ..storylyId = STORYLY_INSTANCE_TOKEN
                      ..storylyTestMode = true,
                  )
                : SizedBox.expand(),
          ),
          TextButton(
            onPressed: changeColor,
            child: Text("Change Color"),
          ),
          Container(
            width: 100,
            height: 100,
            decoration: BoxDecoration(
              gradient: new LinearGradient(
                colors: colors,
                begin: const FractionalOffset(0.0, 0.0),
                end: const FractionalOffset(1.0, 0.0),
                stops: [0.0, 1.0],
                tileMode: TileMode.clamp,
              ),
            ),
          ),
        ],
      ),
    );
  }

  void changeColor() async {
    setState(() {
      isActive = false;
    });

    await Future.delayed(const Duration(milliseconds: 100));

    if (listEquals(colors, [Colors.blue, Colors.green])) {
      colors = [Colors.green, Colors.red];
    } else {
      colors = [Colors.blue, Colors.green];
    }

    setState(() {
      isActive = true;
    });
  }
}
