package Cinema;

import java.time.format.DateTimeFormatter;

public class Constants {

	
	public static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	public static final String SEAT_SEPARATOR = "x"; //TODO použít regulární výraz ve formátu řada x sedadlo
	

}
