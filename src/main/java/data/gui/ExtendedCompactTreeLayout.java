package data.gui;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;
import java.util.List;

/**
 * @author : Pramod Khalkar
 * @since : 23/08/22, Tue
 * description: This file belongs to visual-data-structures
 **/

public class ExtendedCompactTreeLayout extends mxCompactTreeLayout {
    public ExtendedCompactTreeLayout(mxGraph graph) {
        super(graph, false);
        super.prefVertEdgeOff = 0;
    }

    @Override
    public void execute(Object parent) {
        // Execute the CompactTreeLayout
        super.execute(parent);

        // Modify the edges to ensure they exit the source cell at the midpoint
        if (!horizontal) {
            // get all the vertexes
            Object[] vertexes = ((mxGraphModel) graph.getModel()).getChildVertices(graph.getModel(), graph.getDefaultParent());
            for (int i = 0; i < vertexes.length; i++) {
                mxICell parentCell = ((mxICell) (vertexes[i]));
                // For each edge of the vertex
                for (int j = 0; j < parentCell.getEdgeCount(); j++) {
                    mxICell edge = parentCell.getEdgeAt(j);
                    // Only consider edges that are from the cell
                    if (edge.getTerminal(true) != parentCell) {
                        continue;
                    }
                    mxRectangle parentBounds = getVertexBounds(parentCell);
                    List<mxPoint> edgePoints = edge.getGeometry().getPoints();

                    // Need to check that there is always 3 points to an edge, but this will get you started
                    mxPoint outPort = edgePoints.get(0);
                    mxPoint elbowPoint = edgePoints.get(1);
                    if (outPort.getX() != parentBounds.getCenterX()) {
                        outPort.setX(parentBounds.getCenterX());
                        elbowPoint.setX(parentBounds.getCenterX());
                    }
                }
            }
        }
    }
}
