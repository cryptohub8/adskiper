// AdSkipperService.java

package com.example.adskipper;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class AdSkipperService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode != null) {
                findAndClickSkipButton(rootNode);
            }
        }
    }

    private void findAndClickSkipButton(AccessibilityNodeInfo node) {
        if (node == null) return;

        if (node.getText() != null && (node.getText().toString().equalsIgnoreCase("Skip Ad") || node.getText().toString().contains("Skip"))) {
            if (node.isClickable()) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            findAndClickSkipButton(node.getChild(i));
        }
    }

    @Override
    public void onInterrupt() {}
}