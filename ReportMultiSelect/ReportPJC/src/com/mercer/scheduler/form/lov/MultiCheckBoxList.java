package com.mercer.scheduler.form.lov;

import java.awt.Frame;

import java.util.ArrayList;
import oracle.forms.handler.IHandler;
import oracle.forms.properties.ID;
import oracle.forms.ui.CustomEvent;
import oracle.forms.ui.VBean;

public class MultiCheckBoxList extends VBean {

      
    private String getIDs= ""; 
    private String getDesc= "";
    ArrayList<String> ids = new ArrayList();
    ArrayList<String> idsDesc = new ArrayList();
    ArrayList<String> columnHeaders = new ArrayList();
    private int posX;
    private int posY;
    private String formTittle;
    static IHandler mHandler;

    protected static final ID SHOW_MCHECKBOXLIST =ID.registerProperty("SHOW_MCHECKBOXLIST");
    protected static final ID SET_TITLE = ID.registerProperty("SET_TITLE");
    protected static final ID SET_X = ID.registerProperty("SET_X");
    protected static final ID SET_Y = ID.registerProperty("SET_Y");
    protected static final ID ADD_ID = ID.registerProperty("ADD_ID");
    protected static final ID ADD_DESC = ID.registerProperty("ADD_DESC");
    protected static final ID CLEAR = ID.registerProperty("CLEAR");
    protected static final ID SET_COLUMN_HEADER =ID.registerProperty("SET_COLUMN_HEADER");
    protected static final ID GET_ID = ID.registerProperty("GET_ID");
    protected static final ID GET_DESC = ID.registerProperty("GET_DESC");
    
    MultiCheckBoxList() {
        super();
    }
    
    public void init(IHandler handler)
    {
      super.init(handler);
      mHandler = handler;
      // put your initialisation instructions here
    }

    @Override
    public boolean setProperty(ID property, Object value) {
        if (property == ADD_ID) {
            addId(value.toString());
            return true;
        } else if (property == ADD_DESC) {
            addIdDesc(value.toString());
            return true;
        } else if (property == SET_COLUMN_HEADER) {
            addColumnHeader(value.toString());
            return true;
        } else if (property == SET_TITLE) {
            formTittle = value.toString();
            return true;
        } else if (property == SET_X) {
            posX = (int)Double.parseDouble(value.toString());
            return true;
        } else if (property == SET_Y) {
            posY = (int)Double.parseDouble(value.toString());
            return true;
        }     else if (property == CLEAR)
    {
      clearArrays();
      return true;
    }
        else if (property ==
                   SHOW_MCHECKBOXLIST) // Hide/Show the Java Bean component
        {
          
            Frame frame = new Frame();

            MultiCheckBoxListComponent mcb =
                new MultiCheckBoxListComponent(frame, formTittle, true, posX,
                                               posY, ids, idsDesc,
                                               columnHeaders);
            mcb.setVisible(true);
            getIDs=mcb.getOutSelectedIDs();
            getDesc=mcb.getOutSelectedIdDesc();
            clearArrays();
            return true;
        }


        else // default behaviour
        {
            return super.setProperty(property, value);
        }

    }
    
    public Object getProperty(ID property)
    {
      if (property == GET_ID)
      {
        return getIDs ;
      }
      else if (property == GET_DESC)
      {
        return getDesc ;
      }
      else // default behaviour
      {
        return super.getProperty(property);
      }
    }
    /**
      * Send a message to the Forms module
      **/
     public void dispatch_event( ID id )
     {
         CustomEvent ce = new CustomEvent(mHandler, id);
         dispatchCustomEvent(ce);
     }

    private void addId(String id) {
        ids.add(id);
    }

    private void addIdDesc(String idDesc) {
        idsDesc.add(idDesc);
    }

    private void addColumnHeader(String columnHeader) {
        columnHeaders.add(columnHeader);
    }
    private void clearArrays()
    {
        ids.clear();
        idsDesc.clear();
        columnHeaders.clear();
    }
    
               

}
