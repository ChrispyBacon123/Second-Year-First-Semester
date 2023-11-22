SELECT ROUND(((MSRP-buyPrice)/buyPrice)*100,0) AS markup
FROM products 
WHERE productVendor LIKE "Red Start Diecast";