SELECT 
    a.id, a.name, a.price_mad, at.name, a.wait_duration_minutes 'Attente Min'
FROM
    `travelapp-prod`.activity a,
    `travelapp-prod`.activity_type at
WHERE
    at.ID = a.activity_type_id
	
	
	
	Enlevé 2021 - 
	