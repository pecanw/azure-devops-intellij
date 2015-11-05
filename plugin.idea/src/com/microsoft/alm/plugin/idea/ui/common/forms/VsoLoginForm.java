// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See License.txt in the project root.

package com.microsoft.alm.plugin.idea.ui.common.forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.JBUI;
import com.microsoft.alm.plugin.idea.resources.Icons;
import com.microsoft.alm.plugin.idea.resources.TfPluginBundle;
import com.microsoft.alm.plugin.idea.ui.controls.BusySpinnerPanel;
import com.microsoft.alm.plugin.idea.ui.controls.Hyperlink;
import com.microsoft.alm.plugin.idea.ui.controls.IconPanel;
import com.microsoft.alm.plugin.idea.ui.controls.WrappingLabel;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * This form is hosted by the CheckoutPageImpl.
 */
public class VsoLoginForm implements LoginForm {
    private JPanel contentPanel;
    private IconPanel vsIcon;
    private Hyperlink createAnAccountLink;
    private Hyperlink signInLink;
    private BusySpinnerPanel busySpinnerPanel;
    private WrappingLabel descriptionLabel;
    private JLabel headerLabel;
    private JLabel loginProgressLabel;
    private Hyperlink learnMoreLink;
    private boolean initialized = false;

    @Override
    public JPanel getContentPanel() {
        ensureInitialized();
        return contentPanel;
    }

    @Override
    public String getServerName() {
        // Server name cannot be changed for VSO
        return TfPluginBundle.message(TfPluginBundle.KEY_USER_ACCOUNT_PANEL_VSO_SERVER_NAME);
    }

    @Override
    public void setServerName(final String serverName) {
        //ignore for VSO login form
    }

    @Override
    public JComponent getServerNameComponent() {
        //Server name cannot be changed for VSO, we don't allow user to set it via the UI, so return null
        return null;
    }

    @Override
    public void addActionListener(final ActionListener listener) {
        // Hook up listener to all actions
        signInLink.addActionListener(listener);
        createAnAccountLink.addActionListener(listener);
        learnMoreLink.addActionListener(listener);
    }

    @Override
    public void setAuthenticating(boolean inProgress) {
        if (inProgress) {
            busySpinnerPanel.start(true);
            loginProgressLabel.setText(TfPluginBundle.message(TfPluginBundle.KEY_LOGIN_FORM_AUTHENTICATING_VSO));
        } else {
            busySpinnerPanel.stop(true);
            loginProgressLabel.setText("");
        }
    }

    private void createUIComponents() {
        vsIcon = new IconPanel(Icons.VSLogo);
    }

    private void ensureInitialized() {
        if (!initialized) {
            // Ensure that the commands are set up correctly
            signInLink.setActionCommand(CMD_SIGN_IN);
            createAnAccountLink.setActionCommand(CMD_CREATE_ACCOUNT);
            learnMoreLink.setActionCommand(CMD_LEARN_MORE);
            setAuthenticating(false);

            // Ensure that fonts are scaled appropriately
            headerLabel.setFont(JBUI.Fonts.label(20.0f));
            signInLink.setFont(JBUI.Fonts.label(16.0f));

            // Make sure the busy spinner size is scaled properly
            Dimension size = new Dimension(JBUI.scale(20), JBUI.scale(20));
            busySpinnerPanel.setPreferredSize(size);
            busySpinnerPanel.setMinimumSize(size);

            // Add some margin under the description label
            descriptionLabel.setMargin(new Insets(0, 0, JBUI.scale(15), 0));

            initialized = true;
        }
    }

    @Override
    public void initFocus() {
        signInLink.requestFocus();
    }

    /**
     * Getter for unit test
     */
    BusySpinnerPanel getBusySpinner() {
        return this.busySpinnerPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        contentPanel.add(spacer1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        descriptionLabel = new WrappingLabel();
        descriptionLabel.setEnabled(true);
        descriptionLabel.setName("");
        descriptionLabel.setText(ResourceBundle.getBundle("com/microsoft/alm/plugin/idea/ui/tfplugin").getString("VsoLoginForm.Description"));
        contentPanel.add(descriptionLabel, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        headerLabel = new JLabel();
        this.$$$loadLabelText$$$(headerLabel, ResourceBundle.getBundle("com/microsoft/alm/plugin/idea/ui/tfplugin").getString("VsoLoginForm.Header"));
        contentPanel.add(headerLabel, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contentPanel.add(vsIcon, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        busySpinnerPanel = new BusySpinnerPanel();
        contentPanel.add(busySpinnerPanel, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        loginProgressLabel = new JLabel();
        loginProgressLabel.setText("Sample Text for Busy Spinner Message");
        contentPanel.add(loginProgressLabel, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPanel.add(spacer2, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        createAnAccountLink = new Hyperlink();
        this.$$$loadLabelText$$$(createAnAccountLink, ResourceBundle.getBundle("com/microsoft/alm/plugin/idea/ui/tfplugin").getString("VsoLoginForm.CreateAccount"));
        contentPanel.add(createAnAccountLink, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        learnMoreLink = new Hyperlink();
        this.$$$loadLabelText$$$(learnMoreLink, ResourceBundle.getBundle("com/microsoft/alm/plugin/idea/ui/tfplugin").getString("VsoLoginForm.LearnMore"));
        contentPanel.add(learnMoreLink, new GridConstraints(4, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        signInLink = new Hyperlink();
        this.$$$loadLabelText$$$(signInLink, ResourceBundle.getBundle("com/microsoft/alm/plugin/idea/ui/tfplugin").getString("VsoLoginForm.SignIn"));
        contentPanel.add(signInLink, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPanel;
    }
}
