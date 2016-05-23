package com.redhat.eclipseDependencyAnalysis.daWizard;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import com.redhat.eclipseDependencyAnalysis.dependenciesActions.MyDep;

// TODO: Auto-generated Javadoc
/**
 * The Class SCMRevEdittingSupport is used so the column SCM Repository of the table of the Analyse page can be edited and saved. 
 *
 * @author Denis Richtarik
 */
public class SCMRevEdittingSupport extends EditingSupport {

	private TableViewer viewer;
    private CellEditor editor;

    /**
     * Instantiates a new SCM rev editting support.
     *
     * @param viewer the viewer
     */
    public SCMRevEdittingSupport(TableViewer viewer) {
        super(viewer);
        this.viewer = viewer;
        this.editor = new TextCellEditor(viewer.getTable());
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return editor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
      return ((MyDep) element).getScmRev();
    }

    @Override
    protected void setValue(Object element, Object userInputValue) {
      ((MyDep) element).setScmRev(String.valueOf(userInputValue));
      viewer.update(element, null);
    }
}
