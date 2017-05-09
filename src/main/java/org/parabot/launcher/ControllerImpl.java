package org.parabot.launcher;

/**
 * @author JKetelaar
 */
public interface ControllerImpl {
    void setStatus(String status);

    void setStatus(String status, boolean otherThread);

    void fillComponentsFromOtherThread();

    void setReady(int sleep);

    void disableStart(boolean otherThread);
}
