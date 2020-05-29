INSERT INTO Concessionaire (id, name) VALUES(1, 'Concessionaire 1');
INSERT INTO Concessionaire (id, name) VALUES(2, 'Concessionaire 2');
INSERT INTO Concessionaire (id, name) VALUES(3, 'Concessionaire 3');

INSERT INTO Device_Type (id, name) VALUES(1, 'CANBUS');
INSERT INTO Device_Type (id, name) VALUES(2, 'NVR');

INSERT INTO Bus (id, type, motor, brakes, concessionaire_id) VALUES (1, 'Bi-articulado', 'Electric', 'Freno Regenerativo', 1);
INSERT INTO Bus (id, type, motor, brakes, concessionaire_id) VALUES (2, 'Autobus', 'Hybrid', 'Frenos mecánicos', 2);
INSERT INTO Bus (id, type, motor, brakes, concessionaire_id) VALUES (3, 'Bi-articulado', 'Electric', 'Freno Regenerativo', 2);
INSERT INTO Bus (id, type, motor, brakes, concessionaire_id) VALUES (4, 'Buserta', 'Gas', 'Frenos hidráulicos', 3);
INSERT INTO Bus (id, type, motor, brakes, concessionaire_id) VALUES (5, 'Microbus', 'Diesel', 'Frenos mecánicos', 1);

INSERT INTO Device (ip, device_Type_id, bus_id, status) VALUES ('192.168.0.10', 2, 1, 'Active');
INSERT INTO Device (ip, device_Type_id, bus_id, status) VALUES ('192.168.0.11', 2, 2, 'Inactive');
INSERT INTO Device (ip, device_Type_id, bus_id, status) VALUES ('192.168.0.12', 1, 3, 'Active');
INSERT INTO Device (ip, device_Type_id, bus_id, status) VALUES ('192.168.0.13', 1, 4, 'Inactive');
INSERT INTO Device (ip, device_Type_id, bus_id, status) VALUES ('192.168.0.14', 2, 5, 'Active');
