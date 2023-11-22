SELECT customerNumber, creditLimit
FROM customers
WHERE creditLimit<(SELECT creditLimit FROM customers WHERE customerNumber=103);