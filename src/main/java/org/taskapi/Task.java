package org.taskapi;

public class Task {
    private String date;
    private String title;
    private boolean done;
    private String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

   public String toString() {
       StringBuilder sb = new StringBuilder();
           sb.append("date: " + this.date +"\n");
           sb.append(" title: " + this.title+"\n");
           sb.append(" done: " + this.done+"\n");


       return sb.toString();
   }
}
