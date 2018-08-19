
package com.yeah.ruisu.weekend3.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PanelizationSummary implements Serializable
{
    private static final long serialVersionUID = -29238982928391L;

    @Expose
    private Boolean containsEpubBubbles;
    @Expose
    private Boolean containsImageBubbles;

    public Boolean getContainsEpubBubbles() {
        return containsEpubBubbles;
    }

    public void setContainsEpubBubbles(Boolean containsEpubBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
    }

    public Boolean getContainsImageBubbles() {
        return containsImageBubbles;
    }

    public void setContainsImageBubbles(Boolean containsImageBubbles) {
        this.containsImageBubbles = containsImageBubbles;
    }

}
