import { VercelRequest, VercelResponse } from '@vercel/node';
import axios from "axios";  // Or use fetch instead

export default async function handler(req: VercelRequest, res: VercelResponse) {
  try {
    const response = await axios.get('https://jsonplaceholder.typicode.com/todos/1');
    res.status(200).json(response.data);
  } catch (error) {
    res.status(500).json({ message: 'Error fetching data' });
  }
}
