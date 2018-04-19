package org.smartsupply.model.util;

public class ProcessProgress {

    private String id;
    private String task = STARTING;
    private Integer progress;

    public static final String STARTING = "Starting";
    public static final String LOADING_STUDENTS = "Loading Students";
    public static final String LOADING_RESULTS = "Loading Results";
    public static final String MAKING_FILE = "Making Export File";
    public static final String COMPLETED = "Completed";

    public ProcessProgress(String id) {
        this.setId(id);
    }

    public ProcessProgress(String id, Integer progress) {
        this.setId(id);
        this.setProgress(progress);
    }

    public ProcessProgress(String processId, int progress, String task) {
        this.id = processId;
        this.progress = progress;
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getResponseString() {
        return "Process Alive%" + progress + "%" + task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProcessProgress other = (ProcessProgress) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return id + " --  " + progress + " -- " + task;
    }
}
