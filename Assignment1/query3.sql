#Find the customerNumber and creditLimit of all customers who creditLimit is more than 120000
SELECT customerNumber, creditLimit 
FROM customers
WHERE (creditLimit > 120000);