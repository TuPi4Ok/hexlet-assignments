Select first_name, birthday
From users
WHERE birthday > '1999-10-23'
ORDER BY first_name
LIMIT 3;