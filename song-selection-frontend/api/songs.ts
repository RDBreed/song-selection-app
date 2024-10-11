import { VercelRequest, VercelResponse } from '@vercel/node';
import axios from 'axios';
import {getSongApiUrl} from "../lib/constants";

export default async function handler(req: VercelRequest, res: VercelResponse) {
  try {
    const response = await axios.get(`${getSongApiUrl()}/songs`);
    return res.status(200).json(response.data);
  } catch (error) {
    return res.status(500).json({ error: "Failed to fetch songs" });
  }
}
