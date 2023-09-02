
/* 
	Liste des MOM d'un projet
*/

SELECT 
    m.id 'MoM ID', m.title, m.meeting_date, m.attendees, subject_decisions 'Decision', m.is_mo_m_actions_closed 'Closed'
FROM
    `my-pro-apps-prod`.mo_m m
WHERE
    m.projet_id = 10
ORDER BY m.meeting_date