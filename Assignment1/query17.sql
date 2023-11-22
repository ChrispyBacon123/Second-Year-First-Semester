SELECT employees.employeeNumber, offices.city
FROM employees, offices
WHERE employees.officeCode = offices.officeCode;