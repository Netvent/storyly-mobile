//
//  HideStorylyViewController.m
//  StorylyDemo
//
//  Created by Haldun Fadillioglu on 6.05.2021.
//  Copyright © 2021 App Samurai Inc. All rights reserved.
//

#import "HideStorylyViewController.h"

@interface HideStorylyViewController ()
@property StorylyView *storylyView;
@property (weak, nonatomic) IBOutlet UIStackView *storylyViewHolder;
@property bool initialLoad;
@end

@implementation HideStorylyViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.initialLoad = false;
    
    NSString *STORYLY_TOKEN = @"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";
    
    self.storylyView = [[StorylyView alloc] init];
    self.storylyView.storylyInit = [[StorylyInit alloc] initWithStorylyId: STORYLY_TOKEN];
    self.storylyView.rootViewController = self;
    self.storylyView.delegate = self;
    
    [self addColoredViewWithConstraints: [UIColor colorWithRed: 0.00 green: 0.88 blue: 0.89 alpha: 1.00]];
    [self addColoredViewWithConstraints: [UIColor colorWithRed: 0.14 green: 0.14 blue: 0.31 alpha: 1.00]];
    [self addStorylyViewWithConstraint];
    [self addColoredViewWithConstraints: [UIColor colorWithRed: 0.74 green: 0.74 blue: 0.80 alpha: 1.00]];
    [self addColoredViewWithConstraints: [UIColor colorWithRed: 1.00 green: 0.80 blue: 0.00 alpha: 1.00]];
}

- (void)storylyLoaded:(StorylyView *)storylyView
       storyGroupList:(NSArray<StoryGroup *> *)storyGroupList {
    self.initialLoad = true;
}

- (void)storylyLoadFailed:(StorylyView *)storylyView
             errorMessage:(NSString *)errorMessage {
    if (!self.initialLoad) {
        [self.storylyView setHidden:YES];
    }
}

- (void)addStorylyViewWithConstraint {
    [self.storylyViewHolder addArrangedSubview:self.storylyView];
    self.storylyView.translatesAutoresizingMaskIntoConstraints = NO;
    [[self.storylyView.leftAnchor constraintEqualToAnchor:self.view.leftAnchor] setActive:YES];
    [[self.storylyView.rightAnchor constraintEqualToAnchor:self.view.rightAnchor] setActive:YES];
    [[self.storylyView.heightAnchor constraintEqualToConstant:120] setActive:YES];
}

- (void)addColoredViewWithConstraints:(UIColor *)color {
    UIView* view = [[UIView alloc] init];
    view.backgroundColor = color;
    [self.storylyViewHolder addArrangedSubview:view];
    view.translatesAutoresizingMaskIntoConstraints = NO;
    [[view.leftAnchor constraintEqualToAnchor:self.view.leftAnchor] setActive:YES];
    [[view.rightAnchor constraintEqualToAnchor:self.view.rightAnchor] setActive:YES];
    [[view.heightAnchor constraintEqualToConstant:120] setActive:YES];
}


@end
