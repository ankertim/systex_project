INSERT INTO meals (mealID, name, price, description) VALUES (1, 'hamburger', 100, 'This is hamburger');
INSERT INTO meals (mealID, name, price, description) VALUES (2, 'sandwich', 50, 'This is sandwich');
INSERT INTO meals (mealID, name, price, description) VALUES (3, 'juice', 20, 'This is juice');
INSERT INTO meals (mealID, name, price, description) VALUES (4, 'rice', 10, 'This is rice');
INSERT INTO meals (mealID, name, price, description) VALUES (5, 'egg', 20, 'This is egg');
INSERT INTO meals (mealID, name, price, description) VALUES (6, 'apple', 40, 'This is apple');


INSERT INTO orders (orderID, waiter) VALUES (1, 'Bill');
INSERT INTO orders (orderID, waiter) VALUES (2, 'Ann');

INSERT INTO order_details (order_DetailID, orderID, mealID) VALUES (1, 1, 1);
INSERT INTO order_details (order_DetailID, orderID, mealID) VALUES (2, 1, 2);
INSERT INTO order_details (order_DetailID, orderID, mealID) VALUES (3, 1, 3);

INSERT INTO order_details (order_DetailID, orderID, mealID) VALUES (4, 2, 4);
INSERT INTO order_details (order_DetailID, orderID, mealID) VALUES (5, 2, 5);
