//
//  CustomSizeViewController.swift
//  StorylyDemo
//
//  Created by Haldun Melih Fadillioglu on 28.01.2021.
//  Copyright © 2021 App Samurai Inc. All rights reserved.
//

import UIKit
import Storyly

class CustomSizeViewController: UIViewController {
    @IBOutlet weak var storylyViewSmall: StorylyView!
    @IBOutlet weak var storylyViewLarge: StorylyView!
    @IBOutlet weak var storylyViewXLarge: StorylyView!
    @IBOutlet weak var storylyViewPortrait: StorylyView!
    @IBOutlet weak var storylyViewLandscape: StorylyView!

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        storylyViewSmall.storylyInit = StorylyInit(storylyId: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40")
        storylyViewSmall.rootViewController = self
        //storylyViewSmall.storyGroupSize = "small"
        
        storylyViewLarge.storylyInit = StorylyInit(storylyId: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40")
        storylyViewLarge.rootViewController = self
        //storylyViewLarge.storyGroupSize = "large"
        
        storylyViewXLarge.storylyInit = StorylyInit(storylyId: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40")
        storylyViewXLarge.rootViewController = self
        //storylyViewXLarge.storyGroupSize = "xlarge"
        
        
        storylyViewPortrait.storylyInit = StorylyInit(storylyId: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40")
        storylyViewPortrait.rootViewController = self
        //storylyViewPortrait.storyGroupSize = "custom"
        
        //storylyViewPortrait.storyGroupIconStyling = StoryGroupIconStyling(height: 150, width: 100, cornerRadius: 15)
        
         //storylyViewPortrait.storyGroupIconHeight = 150
         //storylyViewPortrait.storyGroupIconWidth = 100
         //storylyViewPortrait.storyGroupIconCornerRadius = 15
        
        storylyViewLandscape.storylyInit = StorylyInit(storylyId: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40")
        storylyViewLandscape.rootViewController = self
        //storylyViewLandscape.storyGroupSize = "custom"
        
        //storylyViewLandscape.storyGroupIconStyling = StoryGroupIconStyling(height: 100, width: 150, cornerRadius: 15)
        
        //storylyViewLandscape.storyGroupIconHeight = 100
        //storylyViewLandscape.storyGroupIconWidth = 150
        //storylyViewLandscape.storyGroupIconCornerRadius = 15
    }

}
