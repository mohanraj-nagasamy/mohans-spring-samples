package com.jsf2.test.common.web;

import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Dimitar Makariev
 */
public class TracePhaseListener implements PhaseListener {

    private static final long serialVersionUID = 1230032804429003338L;
    private static final Logger log = Logger.getLogger(TracePhaseListener.class.getName());

    public void afterPhase(PhaseEvent event) {
        log.warning("afterPhase" + event.getPhaseId());
    }

    public void beforePhase(PhaseEvent event) {
        log.warning("beforePhase" + event.getPhaseId());
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
