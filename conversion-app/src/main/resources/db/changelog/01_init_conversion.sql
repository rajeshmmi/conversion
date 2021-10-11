CREATE TABLE categories (
id SERIAL PRIMARY KEY,
name TEXT
);

CREATE TABLE units (
id SERIAL PRIMARY KEY,
category_id INTEGER REFERENCES Categories(id),
name TEXT
);

CREATE TABLE conversion_chart (
id SERIAL PRIMARY KEY,
category_id INTEGER REFERENCES Categories(id),
from_unit INTEGER REFERENCES Units(id),
to_unit INTEGER REFERENCES Units(id),
conversion_factor NUMERIC(8,3) NOT NULL,
conversion_addend NUMERIC(6,2) NOT NULL
);

INSERT INTO categories (name) VALUES
('Length'),
('Area'),
('Temperature'),
('Volume'),
('Weight');

INSERT INTO units (category_id, name) VALUES
(1, 'Meter'),
(1, 'Kilometer'),
(1, 'Centimeter'),
(1, 'Millimeter'),
(1, 'Mile');

INSERT INTO units (category_id, name) VALUES
(2, 'Square Meter'),
(2, 'Square Kilometer'),
(2, 'Square Centimeter'),
(2, 'Square Millimeter'),
(2, 'Hectare');

INSERT INTO units (category_id, name) VALUES
(3, 'Celsius'),
(3, 'Kelvin'),
(3, 'Fahrenheit');

INSERT INTO units (category_id, name) VALUES
(4, 'Cubic Meter'),
(4, 'Cubic Kilometer'),
(4, 'Cubic Centimeter'),
(4, 'Cubic Millimeter'),
(4, 'Cubic Mile');

INSERT INTO units (category_id, name) VALUES
(5, 'Kilogram'),
(5, 'Gram'),
(5, 'Milligram'),
(5, 'Pound'),
(5, 'Ounce');

INSERT INTO conversion_chart (category_id, from_unit, to_unit, conversion_factor, conversion_addend) VALUES
(1, 1, 1, 1, 0),
(1, 1, 3, 100, 0),
(1, 1, 4, 1000, 0);

INSERT INTO conversion_chart (category_id, from_unit, to_unit, conversion_factor, conversion_addend) VALUES
(2, 6, 6, 1, 0),
(2, 6, 8, 10000, 0);

INSERT INTO conversion_chart (category_id, from_unit, to_unit, conversion_factor, conversion_addend) VALUES
(3, 11, 11, 1, 0),
(3, 11, 12, 0, 273.15),
(3, 11, 13, 1.8, 32),
(3, 12, 11, 0, -273.15),
(3, 12, 12, 1, 0),
(3, 12, 13, 1.8, -459.67),
(3, 13, 11, 0.55, -17.78),
(3, 13, 12, 0.55, 255.37),
(3, 13, 13, 1, 0);

INSERT INTO conversion_chart (category_id, from_unit, to_unit, conversion_factor, conversion_addend) VALUES
(4, 14, 14, 1, 0);

INSERT INTO conversion_chart (category_id, from_unit, to_unit, conversion_factor, conversion_addend) VALUES
(5, 19, 19, 1, 0),
(5, 19, 20, 1000, 0);
