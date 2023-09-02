	SELECT 
    c.code 'Compagny',
    cl.code 'Client',
    p.id, upper(p.name) 'Projet' , 
    upper(pc.code) 'Category',  
    ps.status, 
    ifnull(YEAR(p.start_date),'-') 'Start',
    ifnull(YEAR(p.real_endt_date),'-') 'End',
    p.notes
FROM
    `my-pro-apps-prod`.project p
        LEFT OUTER JOIN
    project_priority pr ON p.project_priority_id = pr.id
        LEFT OUTER JOIN
    project_status_code ps ON ps.id = p.project_status_code_id
    LEFT OUTER JOIN project_category pc on p.project_category_id = pc.id
    LEFT OUTER JOIN company c on c.id=p.company_id
     LEFT OUTER JOIN client cl on cl.id=p.client_code_id
   
ORDER BY pr.ID