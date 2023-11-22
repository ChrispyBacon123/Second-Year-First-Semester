SELECT orderNumber, COALESCE(shippedDate,requiredDate,orderDate) as day 
FROM orders;