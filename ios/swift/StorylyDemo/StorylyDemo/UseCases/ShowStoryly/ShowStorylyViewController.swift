//
//  ShowStorylyViewController.swift
//  StorylyDemo
//
//  Created by Haldun Fadillioglu on 3.05.2021.
//  Copyright © 2021 App Samurai Inc. All rights reserved.
//

import UIKit
import Storyly

class ShowStorylyViewController: UIViewController {

    @IBOutlet weak var storylyHolder: UIStackView!
    var storylyView: StorylyView!
    
    var initialLoad: Bool = true
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let STORYLY_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40"
        
        storylyView = StorylyView()
        storylyView.storylyInit = StorylyInit(storylyId: STORYLY_TOKEN)
        storylyView.rootViewController = self
        storylyView.delegate = self
        storylyView.isHidden = true
        
        addColoredViewWithConstraints(color: UIColor(red: 0.00, green: 0.88, blue: 0.89, alpha: 1.00))
        addColoredViewWithConstraints(color: UIColor(red: 0.14, green: 0.14, blue: 0.31, alpha: 1.00))
        addStorylyViewWithConstraints()
        addColoredViewWithConstraints(color: UIColor(red: 0.74, green: 0.74, blue: 0.80, alpha: 1.00))
        addColoredViewWithConstraints(color: UIColor(red: 1.00, green: 0.80, blue: 0.00, alpha: 1.00))
    }
    
    private func addColoredViewWithConstraints(color: UIColor) {
        let view = UIView()
        view.backgroundColor = color
        storylyHolder.addArrangedSubview(view)
        view.translatesAutoresizingMaskIntoConstraints = false
        view.leftAnchor.constraint(equalTo: storylyHolder.leftAnchor).isActive = true
        view.rightAnchor.constraint(equalTo: storylyHolder.rightAnchor).isActive = true
        view.heightAnchor.constraint(equalToConstant: 120).isActive = true
    }
    
    private func addStorylyViewWithConstraints() {
       self.storylyHolder.addArrangedSubview(self.storylyView)
       self.storylyView.translatesAutoresizingMaskIntoConstraints = false
       self.storylyView.leftAnchor.constraint(equalTo: self.storylyHolder.leftAnchor).isActive = true
       self.storylyView.rightAnchor.constraint(equalTo: self.storylyHolder.rightAnchor).isActive = true
       self.storylyView.heightAnchor.constraint(equalToConstant: 120).isActive = true
   }
}


extension ShowStorylyViewController: StorylyDelegate {

    func storylyLoaded(_ storylyView: Storyly.StorylyView,
                       storyGroupList: [Storyly.StoryGroup]) {
        if initialLoad {
            initialLoad = false
            storylyView.isHidden = false
        }
    }
}
