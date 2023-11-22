SELECT offices.officeCode, count(employees.officeCode) AS size
FROM offices, employees
WHERE offices.officeCode = employees.officeCode
GROUP BY offices.officeCode;