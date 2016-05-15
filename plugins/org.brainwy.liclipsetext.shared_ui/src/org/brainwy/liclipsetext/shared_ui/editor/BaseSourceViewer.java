/**
 * Copyright (c) 2014 by Brainwy Software LTDA. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package org.brainwy.liclipsetext.shared_ui.editor;

import org.brainwy.liclipsetext.shared_core.log.Log;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public abstract class BaseSourceViewer extends ProjectionViewer implements ITextViewerExtensionAutoEditions {

    private boolean autoEditionsEnabled = true;

    public BaseSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles) {
        super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);

        StyledText styledText = this.getTextWidget();
        styledText.setLeftMargin(Math.max(styledText.getLeftMargin(), 2));
    }

    @Override
    public boolean getAutoEditionsEnabled() {
        return autoEditionsEnabled;
    }

    @Override
    public void setAutoEditionsEnabled(boolean b) {
        this.autoEditionsEnabled = b;
    }


    @Override
    protected Layout createLayout() {
        //Workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=438641
        return new RulerLayout(GAP_SIZE_1) {
            @Override
            protected void layout(Composite composite, boolean flushCache) {
                StyledText textWidget = getTextWidget();
                if (textWidget == null) {
                    Log.log("Error: textWidget is already null. SourceViewer: " + BaseSourceViewer.this + " control: "
                            + BaseSourceViewer.this.getControl());
                    return;
                }
                super.layout(composite, flushCache);
            }
        };
    }


}
