SELECT customers.customerNumber, offices.city
FROM customers, employees, offices
WHERE customers.salesRepEmployeeNumber = employees.employeeNumber AND employees.officeCode = offices.officeCode;
