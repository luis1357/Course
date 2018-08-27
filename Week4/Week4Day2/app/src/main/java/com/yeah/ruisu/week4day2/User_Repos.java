package com.yeah.ruisu.week4day2;

public class User_Repos
{
    private String usrRepoName, usrRepoLink, usrRepoLang;

    public User_Repos(String usrRepoName, String usrRepoLink, String usrRepoLang)
    {
        this.usrRepoName = usrRepoName;
        this.usrRepoLink = usrRepoLink;
        this.usrRepoLang = usrRepoLang;
    }

    public String getUsrRepoName() {
        return usrRepoName;
    }

    public void setUsrRepoName(String usrRepoName) {
        this.usrRepoName = usrRepoName;
    }

    public String getUsrRepoLink() {
        return usrRepoLink;
    }

    public void setUsrRepoLink(String usrRepoLink) {
        this.usrRepoLink = usrRepoLink;
    }

    public String getUsrRepoLang() {
        return usrRepoLang;
    }

    public void setUsrRepoLang(String usrRepoLang) {
        this.usrRepoLang = usrRepoLang;
    }
}
