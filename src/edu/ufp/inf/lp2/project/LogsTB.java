package edu.ufp.inf.lp2.project;


import edu.princeton.cs.algs4.BST;

import java.util.Vector;

public class LogsTB {

  public Premium_User mypremUser;

  public Cache mypremCache;


  public BST<Date,TravelBug> myLogsTB;


  public void setPremUser(Premium_User u) {
    this.mypremUser=u;
  }

  public Premium_User getPremUser() {
  return this.mypremUser;
  }

  public void setPremCache(Cache c) {
  if (c.myTipo==Tipo.PREMIUM){
    this.mypremCache=c;
  }else{
    System.out.println("This is not an premium cache ERROR");
  }
  }

  public Cache getPremCache() {
  return this.mypremCache;
  }

}