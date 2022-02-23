import { Author } from './Author'

export type Notice = {
    id: number;
    title: string;
    notice: string;
    author: Author;
    postDate: Date;
}