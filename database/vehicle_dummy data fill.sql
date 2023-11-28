use tollgate_superdb;
#no yet run dueto missing RFID UID codes 
INSERT INTO vehicle (rfidCode,ownerId,vehiclePlate,model,areas,warrants,vehicleClass)
VALUES	('FF12ABCD', '123456789001', 'ZIM0001', 'Mercedes- Benz CLS', '1HGCM82633A123456', '0.0', NULL, '4'),
		('11223344', '123456789002', 'AEX9002', 'Alfa-Romeo GTX', '3VWCM7AJ6AM123456', '0.0', NULL, '4'),
		('11223377', '123456789003', 'ABC1234', 'Pagani-Zonda', 'JTDKB20U477654321', '605.78', 'SpeedingTicket', '4'),
		('11228899', '123456789004', 'AFR5179', 'John-Deer', 'WBA3N5C56EF123456', '0.0', NULL, '5'),
		('22001144', '123456789005', 'AAA1204', 'MAN', 'SAJWA1CZ7E1234567', '0.0', NULL, '2');	
