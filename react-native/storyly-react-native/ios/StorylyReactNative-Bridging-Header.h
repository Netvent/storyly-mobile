//
//  StorylyReactNative-Bridging-Header.h
//  storyly-react-native
//
//  Created by Haldun Melih Fadillioglu on 25.10.2022.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTViewManager.h>


void STLogErr(NSString *msg) { RCTLogError(@"%@", msg); }