INSERT INTO order_details (price, cart_id, id, quantity, watch_id) VALUES
(120, 1, 1, 1, 1);
INSERT INTO order_status (id, status) VALUES
(1, 'PENDING'),
(2, 'CAPTURED'),
(3, 'SHIPPED'),
(4, 'DELIVERED');
INSERT INTO order__ (amount, shipping, tax, customer_id, id, order_date, order_status_id, shipping_address_id)
VALUES (120, 10, 10, 1, 1, '2021-01-01', 3, 1);
-- INSERT INTO order_details (order_id) values (1);
UPDATE order_details SET order_id = 1 WHERE id = 1;

INSERT INTO payment_ (date, id, payment_intent_id, payment_method, type) VALUES
('2021-01-01', 1, 'pi_1', 'card', 'stripe');

-- insert into order__ (payment_id) values (1);
UPDATE order__ SET payment_id = 1 WHERE id = 1;





INSERT INTO order_details (price, cart_id, id, quantity, watch_id) VALUES
    (189, 1, 2, 1, 2);

-- INSERT INTO order_details (order_id) values (1);
UPDATE order_details SET order_id = 1 WHERE id = 2;


