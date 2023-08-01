
/* 
	Liste des MOM d'un projet
*/

SELECT 
    m.id 'MoM ID', m.title, m.meeting_date
FROM
    `my-pro-apps-prod`.mo_m m
WHERE
    m.projet_id = 6
