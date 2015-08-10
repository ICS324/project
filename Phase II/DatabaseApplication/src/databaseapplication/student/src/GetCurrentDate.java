/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aqeil54
 */

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetCurrentDate {
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
Date date = new Date();
public String getDate() {
 return dateFormat.format(date)+"";

  }
}