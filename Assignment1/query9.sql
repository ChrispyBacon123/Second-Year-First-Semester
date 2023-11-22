SELECT customers.customerNumber, customers.city 
FROM customers
WHERE salesRepEmployeeNumber IS NULL
ORDER BY city;