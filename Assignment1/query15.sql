SELECT reportsTo AS manager, COUNT(reportsTo) AS managing
FROM employees
GROUP BY reportsTo
HAVING COUNT(reportsTo)>3;