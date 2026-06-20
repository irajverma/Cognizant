-- 20. User Engagement Index
-- For each user, calculate how many events they attended (completed events they registered for) and how many feedbacks they submitted.

SELECT u.user_id, u.full_name,
       COUNT(DISTINCT CASE WHEN e.status = 'completed' THEN r.event_id END) AS events_attended,
       COUNT(DISTINCT f.feedback_id) AS feedbacks_submitted
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Events e ON r.event_id = e.event_id
LEFT JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name;