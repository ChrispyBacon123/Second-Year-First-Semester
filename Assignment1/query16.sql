SELECT productName, productVendor
FROM products, orderdetails
WHERE products.productCode NOT IN (SELECT orderdetails.productCode FROM orderdetails)
GROUP BY products.productCode;