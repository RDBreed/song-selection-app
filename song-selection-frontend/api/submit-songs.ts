import {VercelRequest, VercelResponse} from '@vercel/node';
import axios from 'axios';
import {getSongApiUrl} from '../lib/constants.js';

export default async function handler(req: VercelRequest, res: VercelResponse) {
  if (req.method !== 'POST') {
    return res.status(405).json({error: "Method Not Allowed"});
  }

  try {
    const response = await axios.post(`${getSongApiUrl()}/submit-songs`, req.body);
    return res.status(200).json(response.data);
  } catch (error) {
    return res.status(500).json({error: "Failed to submit songs"});
  }
}
