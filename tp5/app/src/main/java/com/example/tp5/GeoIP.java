package com.example.tp5;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class GeoIP {
    String status;
    String query;
    String country;
    String countryCode;
    String city;


    @Override
    public String toString() {
        return country + " (" + countryCode + ")\n" + city;
    }

    public void parseXML(XmlPullParser parser ) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        while( eventType!= XmlPullParser.END_DOCUMENT) {
            String name = null;
            switch(eventType)
            {
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if( name.equals("Error")) {
                        System.out.println("Web API Error!");
                    }
                    else if ( name.equals("countryCode")) {
                        countryCode =parser.nextText();
                    }
                    else if (name.equals("query ")) {
                        query = parser.nextText();
                    }
                    else if (name.equals("country")) {
                        country = parser.nextText();
                    }
                    //TODO ajouter d'autres champs
                    else if (name.equals("city")) {
                        city = parser.nextText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
            } // end switch
            eventType = parser.next();
        } // end while
    }
}
