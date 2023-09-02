UPDATE client c 
SET 
    date_creation = CURRENT_TIMESTAMP(),
    created_by = 'admin-louah',
    date_modify =  CURRENT_TIMESTAMP(),
    last_modify_by= "admin-louah"
   
WHERE
    ID > 6