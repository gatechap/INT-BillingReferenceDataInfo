package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils;


import th.truecorp.it.esd.ei.commonlib.utils.constants.ApplicationDefaultValue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils 
{
	public static Date convertToDate(java.sql.Date sqlDate) throws Exception
	{
		Date result = null;
		try
		{
			if (sqlDate != null)
			{
				result = new Date(sqlDate.getTime());
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		
		return result;
	} // end class
	
	public static LocalDateTime convertToLocalDateTime(Date date,ZoneId zoneId) throws Exception
	{
		LocalDateTime result = null;
		
		try
		{
			if (date != null)
			{
				Instant timeInstant = Instant.ofEpochMilli(date.getTime()); 
				result = LocalDateTime.ofInstant(timeInstant, zoneId);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		
		return result;
	} // end class
	
	public static String convertToString(java.sql.Date sqlDate) throws Exception
	{
		String result = "";
		try
		{
			if (sqlDate != null)
			{
				Date date = convertToDate(sqlDate);
				result = convertToString(date, ApplicationDefaultValue.DATE_TIME_FORMATTER, ApplicationDefaultValue.ZONE_ID);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return result;
	} // end method
	
	public static String convertToString(Date date) throws Exception
	{
		String result = "";
		try
		{
			if (date != null)
			{
				result = convertToString(date,ApplicationDefaultValue.DATE_TIME_FORMATTER,ApplicationDefaultValue.ZONE_ID);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return result;
	} // end method
    
    public static String convertToString(Date date, DateTimeFormatter formatter, ZoneId zoneId) throws Exception
    {
    	String result = "";
    	try
    	{
			if (date != null)
			{
				ZonedDateTime zonedDateTime = convertToZonedDateTime(date, zoneId);
				result = zonedDateTime.format(formatter);
			}
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
		return result;
    } // end method
	
	public static ZonedDateTime convertToZonedDateTime(Date date,ZoneId zoneId) throws Exception
	{
		ZonedDateTime result = null;
		
		try
		{
			if (date != null)
			{
				LocalDateTime localDateTime = convertToLocalDateTime(date, zoneId);
				result = ZonedDateTime.of(localDateTime,zoneId);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		
		return result;
	} // end class
    
    public static Date getCurrentDateTime() throws Exception
    {
    	return new Date();
    } // end method
    
    public static java.sql.Date getCurrentSqlDateTime() throws Exception
    {
    	java.sql.Date result = null;
    	try
    	{
    		Date currentDateTime = new Date();
    		result = new java.sql.Date(currentDateTime.getTime());
    	}
    	catch (Exception ex)
    	{
    		throw ex;
    	}
    	
    	return result;
    } // end method
    
    public static void main(String[] arg)
    {
    	try
    	{
    		Date now = DateTimeUtils.getCurrentDateTime();
    		java.sql.Date sqlDateNow = DateTimeUtils.getCurrentSqlDateTime();
    		
//    		System.out.println(DateTimeUtils.convertToString(now, ApplicationDefaultValue.DATE_TIME_FORMAT, ApplicationDefaultValue.REGION_LOCALE));
    		// System.out.println("ZoneId: " + ZoneId.of("Asia/Bangkok"));
    		
//    		LocalDateTime localDateTimeNow = DateTimeUtils.convertToLocalDate(now,ApplicationDefaultValue.ZONE_ID);
//    		System.out.println("localDateTimeNow: " + localDateTimeNow);
    		
//    		ZonedDateTime zonedDateTimeNow = DateTimeUtils.convertToZonedDateTime(now,ApplicationDefaultValue.ZONE_ID);
//    		System.out.println("zonedDateTimeNow: " + zonedDateTimeNow);
    		
    		String dateFormat = DateTimeUtils.convertToString(now, DateTimeFormatter.ISO_OFFSET_DATE_TIME, ApplicationDefaultValue.ZONE_ID);
    		System.out.println("dateFormat: " + dateFormat);
    		
    		String dateFormatDefault = DateTimeUtils.convertToString(sqlDateNow);
    		System.out.println("dateFormat (default): " + dateFormatDefault);
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
} // end class
