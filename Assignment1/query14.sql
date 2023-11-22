SELECT orderNumber, quantityOrdered AS "sum(quantityOrdered)"
FROM orderdetails
WHERE priceEach<30;