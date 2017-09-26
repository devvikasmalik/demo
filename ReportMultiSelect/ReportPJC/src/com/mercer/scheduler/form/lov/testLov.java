package com.mercer.scheduler.form.lov;
import oracle.forms.properties.ID;
public class testLov {
    public static void main(String[] args) {
        boolean call;
        MultiCheckBoxList mcbl=new MultiCheckBoxList();
       call= mcbl.setProperty(mcbl.SET_TITLE, "List of Companies");
       call= mcbl.setProperty(mcbl.SET_X, 200);
       call= mcbl.setProperty(mcbl.SET_Y, 100);
      call=  mcbl.setProperty(mcbl.SET_COLUMN_HEADER, "Company No");
     call=   mcbl.setProperty(mcbl.SET_COLUMN_HEADER, "Company Name");
      call=  mcbl.setProperty(mcbl.SET_COLUMN_HEADER, "Selected");
      call=  mcbl.setProperty(mcbl.ADD_ID, "AU0");
      call=  mcbl.setProperty(mcbl.ADD_ID, "CA0");
     call=   mcbl.setProperty(mcbl.ADD_ID, "US0");  
      call=  mcbl.setProperty(mcbl.ADD_DESC, "AA02 - TEST INACTIVE");
      call=  mcbl.setProperty(mcbl.ADD_DESC, "AE01 - TEST Limited");
      call=  mcbl.setProperty(mcbl.ADD_DESC, "AE02 -  Limited");
   
        call=  mcbl.setProperty(mcbl.SHOW_MCHECKBOXLIST, true);
        call=  mcbl.setProperty(mcbl.CLEAR, "ALL");
        
    }
}
