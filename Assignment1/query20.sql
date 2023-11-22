SELECT customerNumber
FROM payments
GROUP BY customerNumber
ORDER BY COUNT(customerNumber)
LIMIT 1;